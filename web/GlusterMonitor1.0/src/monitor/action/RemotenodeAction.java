package monitor.action;

import hibernate.pojo.GmSite;

import java.util.ArrayList;

import org.apache.catalina.util.ServerInfo;
import org.apache.log4j.Logger;
import org.mortbay.log.Log;

import webservice.infounit.Discinfo;
import webservice.infounit.Nodestat;
import webservice.infounit.Peerinfo;
import webservice.infounit.Sambainfo;
import webservice.infounit.Serverinfo;
import webservice.infounit.Volumeinfo;
import webservice.infounit.Volumestat;
import webservice.interfaces.ClientEngine;

import com.opensymphony.xwork2.ActionSupport;

import database.provider.GmsiteManage;

public class RemotenodeAction extends ActionSupport {
	private int id;
	private ArrayList<String> keys = new ArrayList<String>();
	private ArrayList<Discinfo> disks = new ArrayList<Discinfo>();
	private ArrayList<Nodestat> nodes = new ArrayList<Nodestat>();
	private ArrayList<Sambainfo> sambainfos = new ArrayList<Sambainfo>();
	private Serverinfo serverinfo;
	
	private String result = ""; // for json

	Logger logger = Logger.getLogger(RemotenodeAction.class);

	public ArrayList<Sambainfo> getSambainfos() {
		return sambainfos;
	}

	public void setSambainfos(ArrayList<Sambainfo> sambainfos) {
		this.sambainfos = sambainfos;
	}

	public ArrayList<Nodestat> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Nodestat> nodes) {
		this.nodes = nodes;
	}

	public ArrayList<Discinfo> getDisks() {
		return disks;
	}

	public void setDisks(ArrayList<Discinfo> disks) {
		this.disks = disks;
	}

	public Serverinfo getServerinfo() {
		return serverinfo;
	}

	public void setServerinfo(Serverinfo serverinfo) {
		this.serverinfo = serverinfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<String> getKeys() {
		return keys;
	}

	public void setKeys(ArrayList<String> keys) {
		this.keys = keys;
	}

	public String getInfo() {
		logger.info("request site id " + id);
		GmSite site = new GmsiteManage().getSiteById(id);
		logger.info(site);
		String address = site.getUrl() + ":" + site.getPort();
		ClientEngine cEngine = new ClientEngine(address);
		ArrayList<String> rawkeys = cEngine.getAllKeys();
		
		int length = rawkeys.size();
		for(int i = 0 ; i < length; i ++) {
			if(rawkeys.get(i).contains("get")) {
				Log.info("the key that display " + rawkeys.get(i));
				keys.add(rawkeys.get(i));
			}
		}
		// server info
		ArrayList<String> serverinfostr = cEngine.getInfo("getserverinfo");
		serverinfo = new Serverinfo(serverinfostr);


		// disk info
		ArrayList<String> diskinfostr = cEngine.getInfo("displaydisk");
		for (String str : diskinfostr) {
			Discinfo dinfo = new Discinfo(str);
			logger.info("disk info");
			logger.info("disk" + dinfo.getHostname());
			disks.add(dinfo);
		}

		// node info
		ArrayList<String> nodeinfostr = cEngine.getInfo("getnodestat");
		for (String str : nodeinfostr) {
			Nodestat nstate = new Nodestat(str);
			logger.info("node info");
			logger.info(nstate.getHostname());
			nodes.add(nstate);
		}

		// samba info
		ArrayList<String> saminfostr = cEngine.getInfo("getsambainfo");
		if (saminfostr != null) {
			for (String str : saminfostr) {
				Sambainfo sambainfo = new Sambainfo(str);
				logger.info("samba info");
				sambainfos.add(sambainfo);
			}
		}
		return "success";
	}
}
