package com.example.spinnerdemo.model;

import com.example.spinnerdemo.dto.RewardType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "spinners")
@Entity
@Data
public class Spinner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int value;
    private RewardType rewardType;
    private Double chance;
}
