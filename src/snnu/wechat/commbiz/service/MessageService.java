/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 19, 2014  Create	
 */
package snnu.wechat.commbiz.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import snnu.wechat.commbiz.dao.MessageDAO;
import snnu.wechat.commbiz.entity.Message;
import snnu.wechat.framework.core.vo.BasePageForm;
import snnu.wechat.framework.core.vo.Page;



@Service
@Transactional
public class MessageService  {
	@Autowired
	private MessageDAO messageDAO;

	
	public void save(Message message) {
		//messageDAO.save(message, false);
		if(message.getId()==null){
			message.setCreateTime(new Date());
			message.setLastUpdateTime(new Date());
			messageDAO.save(message);
		}else{
			message.setLastUpdateTime(new Date());
			messageDAO.update(message);
		}

	}
	
	
	public void update(Message message){
		messageDAO.update(message);
	}
	
	public Page<Message> list(BasePageForm form) {
		return messageDAO.queryPageByHql(messageDAO.getFindAllHql(), form);
				
	}

	
	public Message findById(Long id) {
		
		return messageDAO.findById(id);
	}

	
	public void delete(Long id) {
		messageDAO.deleteById(id);
		
	}

}
