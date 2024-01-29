package com.example.spinnerdemo.service;

import com.example.spinnerdemo.model.Spinner;
import com.example.spinnerdemo.repository.SpinnerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SpinService {

    private final SpinnerRepo spinnerRepository;

    public SpinService(SpinnerRepo spinnerRepository) {
        this.spinnerRepository = spinnerRepository;
    }

    public String getSpin() {
        List<Spinner> spinners = spinnerRepository.findAll();
        spinners.sort((s1, s2) -> Double.compare(s2.getChance(), s1.getChance()));
        spinners = spinners.stream().filter(s -> s.getChance() > 0).collect(Collectors.toList());
        Random random = new Random();
        Spinner selectedSpinner = spinners.get(random.nextInt(spinners.size()));
        return selectedSpinner.toString();
    }
}