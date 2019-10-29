package com.yb.demo.converter;

import com.yb.demo.common.exception.ConverterException;
import com.yb.demo.pojo.model.db1.Student1DO;
import com.yb.demo.pojo.response.StudentVO;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 可以自定义转换，也可以直接用BaseConverter的默认实现
 *
 * @author daoshenzzg@163.com
 * @date 2019-10-29 11:12
 */
@Component
public class StudentConverter extends BaseConverter<Student1DO, StudentVO> {

   /*
    @Autowired
    private TeacherService teacherService;
    */

    @Override
    public StudentVO convert(Student1DO from, Class<StudentVO> clazz) throws ConverterException {
        return super.convert(from, clazz);
    }

    @Override
    public List<StudentVO> convert(List<Student1DO> fromList, Class<StudentVO> clazz) throws ConverterException {
        List<StudentVO> voList = super.convert(fromList, clazz);
        // 模拟批量通过学生ID找到对应的老师
        if (!CollectionUtils.isEmpty(voList)) {
            voList.forEach(entry -> entry.setTeacherName("莫言"));
        }
        return voList;
    }
}
