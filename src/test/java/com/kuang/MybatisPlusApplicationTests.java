package com.kuang;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

//    增加一个数据
    @Test
    public  void testInsert(){
        User user = new User();
        user.setId(7);
        user.setName("大炮");
        user.setAge(3);
        user.setEmail("891892301@qq.com");

        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user);
    }
//     更新
    @Test
    public void  testUpdate(){
        User user = new User();
        user.setId(6);
        user.setName("大哈哈哈");
        user.setAge(3);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }
//    测试乐观锁 成功

    @Test
    public void testOptimisticLocker(){
        User user = userMapper.selectById(1);
        user.setName("ahhah");
        user.setAge(4);
        userMapper.updateById(user);

    }

//    测试乐观锁 失败

    @Test
    public void testOptimisticLocker2(){
//        线程1
        User user = userMapper.selectById(1);
        user.setName("ahhah111");
        user.setAge(4);
//        模拟另一个线程执行了插操作
        User user2 = userMapper.selectById(1);
        user.setName("ahhah222");
        user.setAge(4);
        userMapper.updateById(user2);
        userMapper.updateById(user);

    }
//    测试查询
    @Test
    public void  testSelect(){
        User user = userMapper.selectById(2);
        System.out.println(user);
    }
//    批量查询
    @Test
    public  void testSelect1(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }
//    按条件查询之一使用map操作
    @Test
    public void testSelect2(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","大炮");
        map.put("age",3);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }
//    分页
    @Test
    public  void  testPage(){
//        参数一： 当前页
//        参数二： 页面大小
//        使用了分页插件之后，所有的分页操作也变的简单
        Page<User> page = new Page<>(1,3);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }
//    删除
    @Test
    public  void  testDeleteById(){
        userMapper.deleteById("1");
    }
    @Test
    public void  testDeleteById1(){
        userMapper.deleteBatchIds(Arrays.asList(0,1));
    }

}
