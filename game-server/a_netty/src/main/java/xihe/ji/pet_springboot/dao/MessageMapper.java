package xihe.ji.pet_springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestMapping;
import xihe.ji.pet_springboot.pojo.Message;

import java.util.List;

@Mapper
public interface MessageMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    @RequestMapping("BaseResultMap")
    @Select("select * from message where toId=#{id} and pushType= 1")
    List<Message> selectNoPushMessage(String id);

    @Update("update message set  pushType= 2 where toId=#{id}")
    int PushALlMessage(String id);

    @Select("SELECT * FROM message where toId=#{id} GROUP BY fromId DESC")
    List<Message> selectViewMessage(String id);

    @Select("SELECT * FROM message where fromId=#{id} GROUP BY toId DESC")
    List<Message> selectViewMessageTo(String id);

    @Select("SELECT " +
            "  * " +
            "FROM " +
            "  message where EXISTS ( " +
            "    SELECT " +
            "      id " +
            "    FROM " +
            "      message m " +
            "    WHERE " +
            "      toId = #{fromId} " +
            "    AND fromId = #{toId} and message.id=m.id " +
            "  ) or EXISTS ( " +
            "    SELECT " +
            "      id " +
            "    FROM " +
            "      message m " +
            "    WHERE " +
            "      toId = #{toId} " +
            "    AND fromId = #{fromId} and message.id=m.id " +
            "  ) ORDER BY msgTimestamp DESC")
    List<Message> selectHistoryMessage(@Param("fromId") String fromId,@Param("toId") String toId);
}