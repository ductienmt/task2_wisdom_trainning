package com.task2_2.config;

public class API {
    public static final String[] PUBLIC_API = {
            "/api/auth/**",
    };

    public static final String[] ADMIN_API = {
            "/api/accounts/**",
            "/api/categories/save",
            "/api/categories/update",
            "/api/categories/delete",
            "/api/products/save",
            "/api/products/update",
            "/api/products/delete",
            "/api/products/category",
    };

    public static final String[] USER_API = {
            "/api/categories/list",
            "/api/categories/products",
            "/api/products/list",
    };

    public static final String[] ADMIN_USER_API = {
            "/api/categories/list",
            "/api/categories/products",
            "/api/products/list",
    };



}
