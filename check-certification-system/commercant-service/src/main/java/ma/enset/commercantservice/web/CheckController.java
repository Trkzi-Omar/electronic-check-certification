package ma.enset.commercantservice.web;

import lombok.RequiredArgsConstructor;
import ma.enset.commercantservice.dto.CheckRequestDTO;
import ma.enset.commercantservice.dto.CheckResponseDTO;
import ma.enset.commercantservice.service.CheckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commercant/checks")
@RequiredArgsConstructor
public class CheckController {
    private final CheckService checkService;

    @PostMapping
    public ResponseEntity<CheckResponseDTO> createCheck(@RequestBody CheckRequestDTO checkDTO) {
        return ResponseEntity.ok(checkService.saveCheck(checkDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckResponseDTO> getCheck(@PathVariable Long id) {
        return ResponseEntity.ok(checkService.getCheck(id));
    }

    @GetMapping
    public ResponseEntity<List<CheckResponseDTO>> getAllChecks() {
        return ResponseEntity.ok(checkService.getAllChecks());
    }

    @PostMapping("/certify/{checkNumber}")
    public ResponseEntity<CheckResponseDTO> certifyCheck(@PathVariable String checkNumber) {
        return ResponseEntity.ok(checkService.certifyCheck(checkNumber));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheck(@PathVariable Long id) {
        checkService.deleteCheck(id);
        return ResponseEntity.noContent().build();
    }
} 