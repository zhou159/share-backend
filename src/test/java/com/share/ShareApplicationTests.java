package com.share;



import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class ShareApplicationTests {

    @Value("${privateKey}")
    private String privateKey;
    @Value("${publicKey}")
    private String publicKey;


    @Test
    void contextLoads() {
        RSA rsa = new RSA(privateKey,null);
        //私钥加密
        String username = rsa.encryptBase64("username", KeyType.PrivateKey);
        String password = rsa.encryptBase64("password",KeyType.PrivateKey);

        System.out.println(username);
        System.out.println(password);
    }

    @Test
    void test(){
        RSA rsa1 = new RSA(privateKey,publicKey);
        //公钥解密
        String username1 = rsa1.decryptStr("WxLOMdaKnFEgGgxSHDEzBr/Zyupl99DCwWQBELcVAExPk4XFFd9FAPQwPHhEB5J2GX4RT8LaHaSa5pDgEnb5wukKP73xf1GrCxwVs7e7UB5zj3fwqWLxsQY1NRShV1kYcl/QOOG5TDu2HlNibDcComj5BCDOKDvMEJH9ywuObRk=",KeyType.PublicKey);
        String password1 = rsa1.decryptStr("db04rP0YjRXubn8TtwEe87mZXIggr2xwZFajt26KOQ9TeE70uXp8+taTvyyyfc7tItCN9G8KuGyuKjnZIu+WvvxyerIit9Ka4Fn2ipAdhVIVDNvqNP67Z+LKHVFuUv23i8cPsQHULycxtsLJ7U37CGZhfS8qqmRkNvpRScr8Erw=",KeyType.PublicKey);

        System.out.println(username1);
        System.out.println(password1);
    }

    @Test
    void contextLoads2() {
        RSA rsa = new RSA(null,publicKey);
        //公钥加密
        String username = rsa.encryptBase64("11111", KeyType.PublicKey);
        String password = rsa.encryptBase64("11",KeyType.PublicKey);

        System.out.println(username);
        System.out.println(password);
    }

    @Test
    void test2() {
        RSA rsa = new RSA(privateKey,publicKey);
        //私钥解密
        String username = rsa.decryptStr("LRqHDB0cF2vMuuayOBypn1fXNTBL9cSfzgpbmD5ImwyrBTM3DBMoDZI0IoJW8onruwHhOoWRRGtWBD7+Jv4cNFjjqR+691QIs9j5ZfON2CxzByOV3g6u25JbvSbUQ6QhVQz4k5k/7DJlNdOWpZsoWLafQv27GWaR+PBFI5ZKbOM=", KeyType.PrivateKey);
        String password = rsa.decryptStr("GiNu3FdOHrewUKFY3Ow7kjs4pVdF6n+8O2fPhjsiNS6NIzz+1derz9m/m9KHAKYYhikosrHAhAACdXzTspszN+vrUqeGuU8qCCQNN8d8U463jk9zBJGHEAd0EoKjExuCxhG817bd/tuqAirThWIWFi+ODqpC2X6nf+6Y/SsRWAM=",KeyType.PrivateKey);

        System.out.println(username);
        System.out.println(password);
    }

//    @Test
//    void checkCodeTest(){
//
//
//        String code = VerificationCodeUtil.createCode();
//        VerificationCodeUtil.createImage("C:\\Users\\Administrator\\Desktop\\1.jpg",VerificationCodeUtil.createCodeImage(code));
//        System.out.println(code);
//
//    }

    @Test
    public void test3(){
        System.out.println(LocalDateTime.now());
    }



}
