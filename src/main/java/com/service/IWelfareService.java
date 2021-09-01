package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Welfare;

public interface IWelfareService {
	public List<Welfare> findAll();
}
