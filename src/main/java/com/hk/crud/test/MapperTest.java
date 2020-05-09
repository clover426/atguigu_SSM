package com.hk.crud.test;


import com.hk.crud.bean.Employee;
import com.hk.crud.dao.DepartmentMapper;
import com.hk.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试dao层的工作
 * 推荐Spring项目就可以伤脑筋Spring的单元测试，可以自动注入我们需要的组件
 * 1.导入SpringTest模式
 * 2.使用@ContextConfiguration指定Spring配置文件的位置
 * 3.直接autowired要使用的组件即可
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {


    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 批量的sqlSession
     */
    @Autowired
    SqlSession sqlSession;

    /**
     * 测试DepartmentMapper
     */
    @Test
    public void testCRUD(){
        //1.创建Spring ioc容器
//        ApplicationContext applicationContext =
//                new ClassPathXmlApplicationContext("applicationContext.xml");
//        //2.从ioc容器中获取mapper
//        DepartmentMapper bean = applicationContext.getBean(DepartmentMapper.class);
        System.out.println(departmentMapper);

        //1.插入几个部门
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));

//         this.empId = empId;
//        this.empName = empName;
//        this.gender = gender;
//        this.email = email;
//        this.dId = dId;
//        this.department = department;
        //2.测试员工插入
        employeeMapper.insertSelective(new Employee(null,"hk","M","hk520@qq",7));

        //3.批量插入多个员工，使用可以执行批量操作的sqlSession。
        /*
            for(){
               employeeMapper.insertSelective(new Employee(null,"hk","M","hk520@qq",7));
            }
         */
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i <1000 ; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5)+i;
            mapper.insertSelective(
                    new Employee(null,uid,"M",uid+"@qq.com",7));
        }
        System.out.println("完成！");

    }

}
