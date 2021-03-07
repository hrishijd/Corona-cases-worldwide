package com.example.demo.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.StringRefAddr;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.models.Strain;
@Service
public class DataService {
	private List<Strain> list=new ArrayList<>();
	private int nwcs;
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchData() throws IOException, InterruptedException
	{
		List<Strain> lst=new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req=HttpRequest.newBuilder() 
		.uri(URI.create("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv"))
		.build();
		HttpResponse<String> res=client.send(req, HttpResponse.BodyHandlers.ofString());
		System.out.print(res.body());
		StringReader csvr=new StringReader(res.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvr);
		int sum=0;
		for (CSVRecord record : records) {
			Strain st=new Strain();
			int i=Integer.parseInt(record.get(record.size()-1));
			int j=Integer.parseInt(record.get(record.size()-2));
			sum+=(i-j);
		    st.setProvince(record.get("Province/State"));
			st.setCountry(record.get("Country/Region"));
			st.setLatestRp(i);
			System.out.println(st);
			lst.add(st);
		}
		list=lst;
		nwcs=sum;
	}
	public int getNwcs() {
		return nwcs;
	}
	public void setNwcs(int nwcs) {
		this.nwcs = nwcs;
	}
	public List<Strain> getList() {
		return list;
	}
	public void setList(List<Strain> list) {
		this.list = list;
	}
}
