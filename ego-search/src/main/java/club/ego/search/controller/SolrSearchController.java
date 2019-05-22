package club.ego.search.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import club.ego.pojo.TbItem;
import club.ego.search.service.SolrSearchService;

@Controller
public class SolrSearchController {
	
	@Autowired
	private SolrSearchService solrSearchServiceImpl;
	
	@RequestMapping("search.html")
	public void search(Model model, String q,@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "12") int rows) {
		try {
			String query = new String(q.getBytes("iso-8859-1"), "utf-8");
			
			Map<String, Object> map = solrSearchServiceImpl.search(query, page, rows);
			
			model.addAttribute("query", query);
			model.addAttribute("page", map.get("page"));
			model.addAttribute("itemList", map.get("itemList"));
			model.addAttribute("totalPages", map.get("totalPages"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("solr/add")
	@ResponseBody
	int addSolr(@RequestBody Map<String, Object> map) {
		
		try {
			return solrSearchServiceImpl.addSolr((LinkedHashMap)map.get("tbItem"), map.get("desc").toString(), map.get("cat").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
