# Spring-Security

## tag

```
git checkout [tagname]
```
### 01 
> 使用form表单、Basic认证登录

### 02
> 自定义认证-PasswordEncoder

### 03
> 自定义认证-登录页

### 04
> 自定义认证-成功、失败的handler

### 05
> 验证码生成逻辑

## Filter

### UsernamePasswordAuthenticationFilter

`UsernamePasswordAuthenticationFilter`，处理`/login`的`post请求`
```
public UsernamePasswordAuthenticationFilter() {
	super(new AntPathRequestMatcher("/login", "POST"));
}
```
可以通过`HttpSecurity.loginProcessingUrl("/auth/formLogin")`自定义登录url。

## 认证流程源码详解

### 流程
`AbstractAuthenticationProcessingFilter`

`DaoAuthenticationProvider`

`ProviderManager`

### 认证结果在多个请求之间共享
> `SecurityContextPersistenceFilter`

- 请求之前：如果session中有SecurityContext放到线程中
- 请求返回：如果线程中有SecurityContext放到session中

### 获取认证用户信息
@AuthenticationPrincipal UserDetails user

## 增加图像验证码逻辑
过滤器：ValidateCodeFilter

```
// 如果从spring容器中找不到验证码生成器就用默认的imageValidateCodeGenerator
@Bean
@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
```
## 记住我
```
/**
 * 记住我功能的token存取器配置
 * @return
 */
@Bean
public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
    tokenRepository.setDataSource(dataSource);
    return tokenRepository;
}
```
```mysql
create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, 
			token varchar(64) not null, last_used timestamp not null)
```

## 短信登录

```
SmsCodeAuthenticationToken，从UsernamePasswordAuthenticationToken修改来
SmsCodeAuthenticationFilter，拦截请求
SmsCodeAuthenticationProvider，验证逻辑（不验证验证码）
SmsCodeAuthenticationSecurityConfig，将以上类关联起来
```

# Spring-oauth

> 第三方登录sql
```mysql
# userId，业务系统用户id
# providerId，服务提供商Id
# providerUserId，服务提供商的用户Id
create table UserConnection (
    userId varchar(255) not null,
	providerId varchar(255) not null,
	providerUserId varchar(255),
	rank int not null,
	displayName varchar(255),
	profileUrl varchar(512),
	imageUrl varchar(512),
	accessToken varchar(512) not null,
	secret varchar(512),
	refreshToken varchar(512),
	expireTime bigint,
	primary key (userId, providerId, providerUserId));
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);
```

### QQ登录

`/auth/qq`，授权登录的URL。由两段组成：SocialAuthenticationFilter.DEFAULT_FILTER_PROCESSES_URL + provideId（qq）
