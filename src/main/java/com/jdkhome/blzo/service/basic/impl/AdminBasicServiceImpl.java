package com.jdkhome.blzo.service.basic.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jdkhome.blzo.common.component.coder.PasswordEncoder;
import com.jdkhome.blzo.common.component.generator.SaltGenerator;
import com.jdkhome.blzo.common.constant.SqlTemplate;
import com.jdkhome.blzo.common.enums.ResponseError;
import com.jdkhome.blzo.common.exception.ServiceException;
import com.jdkhome.blzo.generator.dao.AdminMapper;
import com.jdkhome.blzo.generator.model.Admin;
import com.jdkhome.blzo.generator.model.AdminExample;
import com.jdkhome.blzo.service.basic.AdminBasicService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jdk on 17/11/16.
 * 管理员管理业务实现类
 */
@Service
public class AdminBasicServiceImpl implements AdminBasicService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdminMapper adminMapper;

    /**
     * 添加管理员
     *
     * @param username 登录名
     * @param password 密码
     * @param nickName
     * @param phone
     * @param remark
     * @return
     */
    @Override
    public Integer addAdmin(String username, String password, String nickName, String phone, String remark) {

        //入参验证
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(nickName) || StringUtils.isEmpty(phone)) {
            logger.error("添加管理员->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setSalt(SaltGenerator.generateUUIDSalt());
        admin.setPassword(PasswordEncoder.toMD5(password, admin.getSalt()));
        admin.setNickName(nickName);
        admin.setPhone(phone);
        admin.setRemark(remark);

        return adminMapper.insertSelective(admin);
    }

    /**
     * 修改管理员
     *
     * @param adminId
     * @param username 登录名
     * @param password 密码
     * @param nickName
     * @param phone
     * @param remark
     * @return
     */
    @Override
    public Integer editAdmin(Integer adminId, String username, String password, String nickName, String phone, String remark) {

        //入参验证
        if (adminId == null) {
            logger.error("修改管理员->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        //获取管理员
        Admin admin = this.getAdminById(adminId);

        if (admin == null) {
            logger.error("修改管理员->管理员 adminId={} 不存在", adminId);
            throw new ServiceException(ResponseError.RESP_ERROR_ADMIN_NOT_EXIST);
        }

        String salt = admin.getSalt();

        admin = new Admin();

        admin.setId(adminId);

        if (StringUtils.isNotEmpty(username)) {
            admin.setUsername(username);
        }

        if (StringUtils.isNotEmpty(password)) {
            admin.setPassword(PasswordEncoder.toMD5(password, salt));
        }

        if (StringUtils.isNotEmpty(nickName)) {
            admin.setNickName(nickName);
        }

        if (StringUtils.isNotEmpty(phone)) {
            admin.setPhone(phone);
        }

        if (StringUtils.isNotEmpty(remark)) {
            admin.setRemark(remark);
        }


        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    /**
     * 删除管理员
     *
     * @param adminId
     * @return
     */
    @Override
    public Integer delAdmin(Integer adminId) {

        if (adminId == null) {
            logger.error("删除管理员->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        return adminMapper.deleteByPrimaryKey(adminId);
    }

    /**
     * 获取管理员通过Id
     *
     * @param adminId
     * @return
     */
    @Override
    public Admin getAdminById(Integer adminId) {

        //入参验证
        if (adminId == null) {
            logger.error("获取管理员通过Id->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        return adminMapper.selectByPrimaryKey(adminId);
    }

    /**
     * 获取管理员通过username
     *
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUsername(String username) {

        if (StringUtils.isEmpty(username)) {
            logger.error("获取管理员通过username->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(username);

        List<Admin> objs = adminMapper.selectByExample(example);
        if (objs != null && !objs.isEmpty()) {
            return objs.get(0);
        }
        return null;
    }


    private AdminExample getExample(String username, String nickName, String phone) {

        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }

        if (StringUtils.isNotEmpty(nickName)) {
            criteria.andNickNameLike("%" + nickName + "%");
        }

        if (StringUtils.isNotEmpty(phone)) {
            criteria.andPhoneLike("%" + phone + "%");
        }

        example.setOrderByClause(SqlTemplate.ORDER_BY_ID_DESC);

        return example;
    }

    /**
     * 分页查询管理员
     *
     * @param username
     * @param nickName
     * @param phone
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Admin> getAdminsWithPage(String username, String nickName, String phone, Integer page, Integer size) {

        //入参验证
        if (page == null || size == null) {
            logger.error("分页查询管理员->分页参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        AdminExample example = this.getExample(username, nickName, phone);


        PageHelper.startPage(page, size);
        PageInfo pageInfo = new PageInfo<>(adminMapper.selectByExample(example));

        return pageInfo;
    }

    /**
     * 获取所有管理员
     *
     * @param username
     * @param nickName
     * @param phone
     * @return
     */
    @Override
    public List<Admin> getAllAdmin(String username, String nickName, String phone) {

        AdminExample example = this.getExample(username, nickName, phone);

        return adminMapper.selectByExample(example);
    }
}
