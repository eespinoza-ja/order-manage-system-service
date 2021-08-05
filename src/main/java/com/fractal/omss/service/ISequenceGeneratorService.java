package com.fractal.omss.service;

import org.springframework.stereotype.Service;

@Service
public interface ISequenceGeneratorService {
	public long generateSequence(String sequenceName);
}
