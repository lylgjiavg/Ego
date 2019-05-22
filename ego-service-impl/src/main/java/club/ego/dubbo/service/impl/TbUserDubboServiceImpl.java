package club.ego.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import club.ego.dubbo.service.TbUserDubboService;
import club.ego.mapper.TbUserMapper;
import club.ego.pojo.TbUser;
import club.ego.pojo.TbUserExample;

public class TbUserDubboServiceImpl implements TbUserDubboService {

	@Autowired
	private TbUserMapper tbUserMapper;
	
	@Override
	public TbUser selectByUandP(TbUser tbuser) {
		
		TbUserExample example = new TbUserExample();
		example.createCriteria().andUsernameEqualTo(tbuser.getUsername()).andPasswordEqualTo(tbuser.getPassword());
		
		List<TbUser> result = tbUserMapper.selectByExample(example);
		if(result!=null&&result.size()>0) {
			return result.get(0);
		}
		
		return null;
	}

}
