package org.zpp.security.rbac.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zpp.security.rbac.domain.Admin;
import org.zpp.security.rbac.domain.Resource;
import org.zpp.security.rbac.dto.ResourceInfo;
import org.zpp.security.rbac.repository.AdminRepository;
import org.zpp.security.rbac.repository.ResourceRepository;
import org.zpp.security.rbac.service.ResourceService;

import java.util.List;


@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceRepository resourceRepository;
	@Autowired
	private AdminRepository adminRepository;


	@Override
	public ResourceInfo getTree(Long adminId) {
		Admin admin = adminRepository.findById(adminId).get();
		return resourceRepository.findByName("根节点").toTree(admin);
	}

	@Override
	public ResourceInfo getInfo(Long id) {
		Resource resource = resourceRepository.findById(id).get();
		ResourceInfo resourceInfo = new ResourceInfo();
		BeanUtils.copyProperties(resource, resourceInfo);
		return resourceInfo;
	}

	@Override
	public ResourceInfo create(ResourceInfo info) {
		Resource parent = resourceRepository.findById(info.getParentId()).get();
		if(parent == null){
			parent = resourceRepository.findByName("根节点");
		}
		Resource resource = new Resource();
		BeanUtils.copyProperties(info, resource);
		parent.addChild(resource);
		info.setId(resourceRepository.save(resource).getId());
		return info;		
	}

	@Override
	public ResourceInfo update(ResourceInfo info) {
		Resource resource = resourceRepository.findById(info.getId()).get();
		BeanUtils.copyProperties(info, resource);
		return info;
	}

	@Override
	public void delete(Long id) {
		resourceRepository.deleteById(id);
	}


	@Override
	public Long move(Long id, boolean up) {
		Resource resource = resourceRepository.findById(id).get();
		int index = resource.getSort();
		List<Resource> childs = resource.getParent().getChilds();
		for (int i = 0; i < childs.size(); i++) {
			Resource current = childs.get(i);
			if(current.getId().equals(id)) {
				if(up){
					if(i != 0) {
						Resource pre = childs.get(i - 1);
						resource.setSort(pre.getSort());
						pre.setSort(index);
						resourceRepository.save(pre);
					}
				}else{
					if(i != childs.size()-1) {
						Resource next = childs.get(i + 1);
						resource.setSort(next.getSort());
						next.setSort(index);
						resourceRepository.save(next);
					}
				}
			}
		}
		resourceRepository.save(resource);
		return resource.getParent().getId();
	}

}
