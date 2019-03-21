package com.sy.springbootdemo1.realm;

import com.sy.springbootdemo1.dao.UsersMapper;
import com.sy.springbootdemo1.pojo.Users;
import com.sy.springbootdemo1.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import sun.nio.cs.US_ASCII;

@SuppressWarnings("all")
public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    private UserService service;

//    @Autowired
//    private UsersService usersService;

    /**
     * 用于获取登录成功后的角色、权限等信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 验证当前登录的Subject
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        System.out.println("username=:" + username);
        Users user = service.findOnByName(username);
        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
//        if (Boolean.TRUE.equals(user.getLocked())) {
//            throw new LockedAccountException(); //帐号锁定
//        }
        return new SimpleAuthenticationInfo(
                user.getAccount(), //用户名
                user.getPwd(), //密码
//                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
    }
}
