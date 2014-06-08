package monitor.action;

import hibernate.pojo.GmSite;
import hibernate.pojo.GmStatus;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.abdera.protocol.Request;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.mortbay.log.Log;

import webservice.infounit.Peerinfo;
import webservice.infounit.Sambainfo;
import webservice.infounit.Volumeinfo;
import webservice.infounit.Volumestat;
import webservice.interfaces.ClientEngine;
import webservice.interfaces.Statedetector;

import com.opensymphony.xwork2.ActionSupport;

import database.provider.GmsiteManage;
import database.provider.GmstatusManage;
import database.provider.SQLinterface;

public class AjaxAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;

	private String result;
	private String hostname;
	private Logger logger = Logger.getLogger(AjaxAction.class);

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void makeJSON(ArrayList<Object> status) {
		String memstr = "", cpustr = "", timestr = "";
		String netsendstr = "", netreceivestr = "";
		String ibsendstr = "", ibreceivestr = "";
		DecimalFormat df = new DecimalFormat("#.00");
		SimpleDateFormat timedf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("status:" + status.size());
		for (Object tmp : status) {
			if (tmp instanceof GmStatus) {
				GmStatus gmStatus = (GmStatus) tmp;
				timestr += timedf.format(gmStatus.getId().getId()).toString()
						+ ",";
				memstr += df.format(gmStatus.getMemusedratio() * 100.0) + ",";
				cpustr += gmStatus.getCpuuseratio() + ",";
				netsendstr += gmStatus.getNetsendflow() + ",";
				netreceivestr += gmStatus.getNetreceiveflow() + ",";
				ibsendstr += gmStatus.getIbsendflow() + ",";
				ibreceivestr += gmStatus.getIbreceiveflow() + ",";
			}
		}
		timestr = timestr.substring(0, timestr.length() - 1);
		memstr = memstr.substring(0, memstr.length() - 1);
		cpustr = cpustr.substring(0, cpustr.length() - 1);
		netsendstr = netsendstr.substring(0, netsendstr.length() - 1);
		netreceivestr = netreceivestr.substring(0, netreceivestr.length() - 1);
		ibsendstr = ibsendstr.substring(0, ibsendstr.length() - 1);
		ibreceivestr = ibreceivestr.substring(0, ibreceivestr.length() - 1);

		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("memory", memstr);
			map.put("cpu", cpustr);
			map.put("time", timestr);
			map.put("netsend", netsendstr);
			map.put("netreceive", netreceivestr);
			map.put("ibsend", ibsendstr);
			map.put("ibreceive", ibreceivestr);
			logger.info("begin json");
			JSONObject json = JSONObject.fromObject(map);
			logger.info("end json");
			result = json.toString();
			logger.info(result);
		} catch (Exception e) {
			logger.info(e.toString());
		}
	}

	public String getLineinfo() {
		ArrayList<Object> status = new ArrayList<Object>();
		GmstatusManage sqLinterface = new GmstatusManage();
		String id = request.getParameter("siteid");
		GmSite site = new GmsiteManage().getSiteById(Integer.parseInt(id));
		String hostname = site.getDescription();
		logger.info("hostname:" + hostname);
		status = sqLinterface.getListByHostname(hostname);
		logger.info("request for " + hostname);
		makeJSON(status);
		return "success";
	}

	public String getCurrentLineinfo() {
		ArrayList<Object> status = new ArrayList<Object>();
		GmstatusManage sqLinterface = new GmstatusManage();
		String id = request.getParameter("siteid");
		String lasttime = request.getParameter("lasttime");
		GmSite site = new GmsiteManage().getSiteById(Integer.parseInt(id));
		String hostname = site.getDescription();
		logger.info("hostname:" + hostname);
		status = sqLinterface.getListByHostnameandTime(hostname, lasttime);
		makeJSON(status);
		return "success";
	}

	public String getResultOfLogs() {
		ArrayList<Object> status = new ArrayList<Object>();
		GmstatusManage sqLinterface = new GmstatusManage();
		String id = request.getParameter("siteid");
		String date = request.getParameter("date");
		GmSite site = new GmsiteManage().getSiteById(Integer.parseInt(id));
		String hostname = site.getDescription();
		logger.info("hostname:" + hostname);
		status = sqLinterface.getLogsdetail(hostname, date);
		DecimalFormat df = new DecimalFormat("#.00");
		SimpleDateFormat timedf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result = "<table class='table table-bordered'>" + "<tr>"
				+ "<th>时间</th>" + "<th>主机名</th>" + "<th>ib地址</th>"
				+ "<th>内存使用率（%）</th>" + "<th>cpu使用率（%）</th>"
				+ "<th>net发送量（kb）</th>" + "<th>net接受量（kb）</th>"
				+ "<th>ib发送量（kb）</th>" + "<th>ib接受量（kb）</th>" + "</tr>";

		for (Object tmp : status) {
			if (tmp instanceof GmStatus) {
				GmStatus gs = (GmStatus) tmp;
				result += "<tr>";
				result += "<td>" + timedf.format(gs.getId().getId()) + "</td>"
						+ "<td>" + gs.getId().getHostname() + "</td>" + "<td>"
						+ gs.getIpofib() + "</td>" + "<td>"
						+ df.format(gs.getMemusedratio() * 100) + "</td>"
						+ "<td>" + gs.getCpuuseratio() + "</td>" + "<td>"
						+ gs.getNetsendflow() + "</td>" + "<td>"
						+ gs.getNetreceiveflow() + "</td>" + "<td>"
						+ gs.getIbsendflow() + "</td>" + "<td>"
						+ gs.getIbreceiveflow() + "</td>";
				result += "</tr>";
			}
		}
		result += "</table>";
		return "success";

	}

	public String getResultofCommand() {
		String id = request.getParameter("siteid");
		String cmd = request.getParameter("cmd");
		GmSite site = new GmsiteManage().getSiteById(Integer.parseInt(id));
		logger.info("request from site " + id + " cmd:" + cmd);
		ClientEngine cEngine = new ClientEngine(site.getAddress());
		ArrayList<String> resList = cEngine.getInfo(cmd);
		result = "";
		for (String tmp : resList) {
			result += tmp + "<br>";
		}
		return "success";
	}

	public String getSiteinfo() {
		String siteid = request.getParameter("siteid");
		GmsiteManage gm = new GmsiteManage();
		GmSite gs = gm.getSiteById(Integer.parseInt(siteid));
		String address = gs.getAddress();
		logger.info("check address " + address);
		if (Statedetector.isOnline(address))
			result = "true";
		else
			result = "false";
		Log.info("this site is online? " + result);
		return "success";
	}

	// get volume status
	public String getVolumestatus() {
		// get Engine
		String siteid = request.getParameter("siteid");
		int id = Integer.parseInt(siteid);
		logger.info("request volume status id " + id);
		GmSite site = new GmsiteManage().getSiteById(id);
		logger.info(site);
		String address = site.getUrl() + ":" + site.getPort();
		ClientEngine cEngine = new ClientEngine(address);
		ArrayList<Volumestat> vols = new ArrayList<Volumestat>();
		// volume state
		ArrayList<String> volumestatstr = cEngine.getInfo("getvolstat");
		if (volumestatstr != null) {
			for (String str : volumestatstr) {
				Volumestat vstat = new Volumestat(str);
				logger.info("vol state");
				logger.info(vstat.getVolume());
				vols.add(vstat);
			}
		}
		result = "";
		for (Volumestat tmp : vols) {
			result += "<tr>";
			result += "<td>" + tmp.getVolume() + "</td>";
			result += "<td>" + tmp.getUsed() + "</td>";
			result += "<td>" + tmp.getAvailable() + "</td>";
			result += "<td>" + tmp.getMounton() + "</td>";
			result += "<td><button type='button' class='btn btn-danger btn-xs'>取消挂载</button></td>";
			result += "</tr>";
		}

		return "success";
	}

	public String getVolumeinfo() {
		String siteid = request.getParameter("siteid");
		int id = Integer.parseInt(siteid);
		logger.info("request volume info id " + id);
		GmSite site = new GmsiteManage().getSiteById(id);
		logger.info(site);
		String address = site.getUrl() + ":" + site.getPort();
		ClientEngine cEngine = new ClientEngine(address);
		ArrayList<Volumeinfo> volinfos = new ArrayList<Volumeinfo>();
		ArrayList<String> volumeinfostr = cEngine.getInfo("getvolinfo");
		if (volumeinfostr != null) {
			for (String str : volumeinfostr) {
				Volumeinfo vinfo = new Volumeinfo(str);
				logger.info("vol info");
				volinfos.add(vinfo);
			}
		}

		result = "";
		for (Volumeinfo tmp : volinfos) {
			result += "<tr>";
			result += "<td>" + tmp.getVolumename() + "</td>";
			result += "<td>" + tmp.getVolumetype() + "</td>";
			result += "<td>" + tmp.getTransporttype() + "</td>";
			result += "<td>" + tmp.getVolumestatus() + "</td>";
			result += "<td>" + tmp.getBricknum() + "</td>";
			result += "<td>" + tmp.getRepfac() + "</td>";
			result += "<td>" + tmp.getBrickelement() + "</td>";
			
			if(tmp.getVolumestatus().equals("Started")) {
				result += "<td><button type='button' class='btn btn-warning btn-stop btn-xs'>停止卷</button></td>";
			}else {
				result += "<td><button type='button' class='btn btn-primary btn-start btn-xs'>开启卷</button>";
				result += "<button type='button' class='btn btn-danger btn-delete btn-xs'>删除卷</button>";
			}
			result += "</tr>";
		}
		return "success";
	}

	public String getPeerinfo() {
		String siteid = request.getParameter("siteid");
		int id = Integer.parseInt(siteid);
		logger.info("request volume info id " + id);
		GmSite site = new GmsiteManage().getSiteById(id);
		logger.info(site);
		String address = site.getUrl() + ":" + site.getPort();
		ClientEngine cEngine = new ClientEngine(address);
		// peer state
		ArrayList<Peerinfo> peers = new ArrayList<Peerinfo>();
		ArrayList<String> peerinfostr = cEngine.getInfo("getpeerstatus");
		for (String str : peerinfostr) {
			Peerinfo pinfo = new Peerinfo(str);
			logger.info("peer info");
			peers.add(pinfo);
		}

		result = "";
		for (Peerinfo tmp : peers) {
			result += "<tr>";
			result += "<td>" + tmp.getPeer() + "</td>";
			result += "<td>" + tmp.getConnectstatus() + "</td>";
			result += "<td>" + tmp.getIpaddress() + "</td>";
			result += "<td><button type='button' class='btn btn-danger btn-delete-node btn-xs'>删除节点</button></td>";
			result += "</tr>";
		}
		return "success";
	}

	public String getSambaInfo() {
		String siteid = request.getParameter("siteid");
		int id = Integer.parseInt(siteid);
		logger.info("request volume info id " + id);
		GmSite site = new GmsiteManage().getSiteById(id);
		logger.info(site);
		String address = site.getUrl() + ":" + site.getPort();
		ClientEngine cEngine = new ClientEngine(address);
		// samba info
		ArrayList<Sambainfo> sambainfos = new ArrayList<Sambainfo>();
		ArrayList<String> saminfostr = cEngine.getInfo("getsambainfo");
		if (saminfostr != null) {
			for (String str : saminfostr) {
				Sambainfo sambainfo = new Sambainfo(str);
				logger.info("samba info");
				sambainfos.add(sambainfo);
			}
		}
		
		result = "";
		for (Sambainfo tmp : sambainfos) {
			result += "<tr>";
			result += "<td>" + tmp.getDirname() + "</td>";
			result += "<td>" + tmp.getPath() + "</td>";
			result += "<td>" + tmp.getGuest() + "</td>";
			result += "<td>" + tmp.getReadonly() + "</td>";
			result += "<td>" + tmp.getGroup() + "</td>";
			result += "<td>" + tmp.getUser() + "</td>";
			result += "<td><button type='button' class='btn btn-danger btn-delete-samba btn-xs'>删除</button></td>";
			result += "</tr>";
		}
		
		return "success";
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;

	}
}
