package com.example.springboardlv3.entity;

public enum UserRoleEnum { // 여기 부분은 권한을 허용 관리를 하는 부분으로써 꼭 필요하죠?
    USER(Authority.USER),  // 사용자 권한
    ADMIN(Authority.ADMIN);  // 관리자 권한

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
    
}
