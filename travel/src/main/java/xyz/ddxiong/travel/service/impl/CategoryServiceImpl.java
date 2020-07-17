package xyz.ddxiong.travel.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSession;
import redis.clients.jedis.Jedis;
import xyz.ddxiong.travel.pojo.Category;
import xyz.ddxiong.travel.service.CategoryService;
import xyz.ddxiong.travel.dao.CategoryMapper;
import xyz.ddxiong.travel.utils.JedisUtil;
import xyz.ddxiong.travel.utils.MybatisUtils;

import java.util.List;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月17日 18:13:00
 * @Description: TODO
 */
public class CategoryServiceImpl implements CategoryService {

    @Override
    public String findCategory() {
        /**
         * 利用Jedis缓存
         */
        Jedis jedis = JedisUtil.getJedis();
        String categoryJson = jedis.get("CategoryJosn");
        try {
            if (categoryJson == null) {
                SqlSession sqlSession = MybatisUtils.getSqlSession();
                CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
                List<Category> allCategory = mapper.findAllCategory();
                ObjectMapper objectMapper = new ObjectMapper();
                categoryJson = objectMapper.writeValueAsString(allCategory);
                jedis.set("CategoryJson",categoryJson);
                MybatisUtils.closeSession(sqlSession);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JedisUtil.close(jedis);
        return categoryJson;
    }
}
