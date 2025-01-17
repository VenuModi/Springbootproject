package com.example.springbootproject.controller;

import com.example.springbootproject.entity.Member;
import com.example.springbootproject.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.channels.MembershipKey;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberRepository repository;

    public MemberController(MemberRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    Member getAMember(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    List<Member> getMembers() {
        return repository.findAll();
    }

    @PostMapping
    void addMember(@RequestBody Member member) {
        String name = member.getName();
        if (name == null || name.isEmpty())
            throw new IllegalStateException();
        repository.save(member);
    }

    @DeleteMapping("/{id}")
    @Transactional
    void deleteMember(@PathVariable Long id) {
        if (repository.findById(id).isPresent())
            repository.deleteById(id);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
        var updateMember = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateMember.setName(memberDetails.getName());
        updateMember.setEmail(memberDetails.getEmail());
        updateMember.setPhoneNumber(memberDetails.getPhoneNumber());

        repository.save(updateMember);

        return ResponseEntity.ok(updateMember);
    }
}
