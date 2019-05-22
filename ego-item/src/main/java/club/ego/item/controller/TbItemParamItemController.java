package club.ego.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import club.ego.item.pojo.ParamItem;
import club.ego.item.pojo.ParamItemKV;
import club.ego.item.service.TbItemParamItemItemService;

@Controller
public class TbItemParamItemController {

	@Autowired
	private TbItemParamItemItemService tbItemParamItemItemServiceImpl;

	@RequestMapping(value = "item/param/{id}.html", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String showParamItem(@PathVariable long id) {

		List<ParamItem> paramItems = tbItemParamItemItemServiceImpl.showItemParamItem(id);
		StringBuffer buffer = new StringBuffer();
		for (ParamItem paramItem : paramItems) {
			buffer.append("<table width='40%'>");

			String group = paramItem.getGroup();
			List<ParamItemKV> params = paramItem.getParams();
			for (int i = 0; i < params.size(); i++) {
				if (i == 0) {
					buffer.append("<tr >");
					buffer.append("<td>" + group + "</td>");
					buffer.append("<td align='right' width='30%'>" + params.get(i).getK()+"   :" + "</td>");
					buffer.append("<td width='30%'>" + params.get(i).getV() + "</td>");
					buffer.append("</tr>");
				} else {
					buffer.append("<tr>");
					buffer.append("<td>" + "" + "</td>");
					buffer.append("<td align='right' width='30%'>" + params.get(i).getK()+"   :" + "</td>");
					buffer.append("<td width='30%'>" + params.get(i).getV() + "</td>");
					buffer.append("</tr>");
				}
			}
			buffer.append("</table>");
			buffer.append("<hr>");
		}

		return buffer.toString();
	}

}
