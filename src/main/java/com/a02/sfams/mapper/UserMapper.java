package com.a02.sfams.mapper;

import com.a02.sfams.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper

public interface UserMapper extends BaseMapper<User> {

//    @Select("select * from person")
//    public List<Person>getAll();
//
//
//
//    @Insert("insert into person values (#{id},#{number},#{name},#{phone},#{password},#{mail},#{apartmentId},#{authority})")
//    public int insert(Person person);
//
//
//
//    @Update("update person set number=#{number},name=#{name},phone=#{phone},password=#{password},mail=#{mail},apartment_id=#{apartmentId},authority=#{authority} where id=#{id}")
//    public int update(Person person);
//
//
//
//    @Select("select * from person where id =#{id}")
//    public Person findById(int id);
//
//    @Delete("delete from person where id =#{id}")
//    public int delete(int id);



    public List<User> findAll();
}

