package club.ego.search.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import club.ego.search.service.SolrInitService;

@Controller
public class SolrInitController {
	
	@Autowired
	private SolrInitService solrInitServiceImpl;
	
	@RequestMapping(value = "solr/init",produces = "text/html;charset=utf-8")
	@ResponseBody
	public String init() {
		try {
			long start = new Date().getTime();
			solrInitServiceImpl.init();
			long end = new Date().getTime();
			return "初始化成功:" + (end-start)/1000 + "秒";
		} catch (Exception e) {
			e.printStackTrace();
			return "初始化失败";
		}
		
	}
	
}
