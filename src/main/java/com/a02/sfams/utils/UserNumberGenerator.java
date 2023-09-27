package com.a02.sfams.utils;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

public class UserNumberGenerator implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        return IdGenerator.generateId();
    }

}
