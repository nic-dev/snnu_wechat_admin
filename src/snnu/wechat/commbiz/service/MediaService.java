/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 19, 2014  Create	
 */
package snnu.wechat.commbiz.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import snnu.wechat.commbiz.dao.MediaDAO;
import snnu.wechat.commbiz.entity.Media;
import snnu.wechat.commbiz.web.form.MediaQueryForm;
import snnu.wechat.framework.core.vo.Page;
import snnu.wechat.framework.dao.hql.Hql;
import snnu.wechat.framework.dao.hql.Parameter;


@Service
@Transactional
public class MediaService {
	@Autowired
	private MediaDAO mediaDAO;

	public Page<Media> list(MediaQueryForm form) {
		// return mediaDAO.find(mediaDAO.getFindAllHql()+" where type="+type +
		// " order by id desc");
		String hqlStr = mediaDAO.getFindAllHql();
		if (form.getType() != null) {
			hqlStr += " where  type=" + form.getType();
		}
		return mediaDAO.queryPageByHql(hqlStr, form);
	}

	public void delete(Long id) {
		mediaDAO.deleteById(id);
	}

	public void updateName(Long id, String name) {
		String hqlStr = "update Media set name=:name where id=:id";
		Hql hql = new Hql(hqlStr);

		hql.setParameterList(Arrays.asList(new Parameter[] { new Parameter("name", name),
				new Parameter("id", id) }));
		mediaDAO.executeUpdate(hql);
	}

	public void save(Media media) {
		media.setLastUpdateTime(new Date());
		if (media.getId() == null) {
			media.setCreateTime(new Date());

			mediaDAO.save(media);
		} else {
			mediaDAO.update(media);
		}
	}

	public Media findById(Long id) {
		return mediaDAO.findById(id);
	}

}
