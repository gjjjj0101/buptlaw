package edu.bupt.law.common.constants;

import java.util.HashMap;
import java.util.Map;

public enum RolesConstants {

    User("ROLE_USER",1),
    Group_Admin("ROLE_GROUP_ADMIN",2),
    Area_Admin("ROLE_AREA_ADMIN",3);


    private String name;
    private Integer level;

    RolesConstants(String name, Integer level){
        this.level = level;
        this.name = name;
    }

    private static final Map<Integer, String> mappings = new HashMap<>(16);

    static {
        for (RolesConstants rolesConstants : values()) {
            mappings.put(rolesConstants.level, rolesConstants.name);
        }
    }

    public static String getLevelString(Integer role) {
        return mappings.get(role);
    }
}
