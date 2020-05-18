package kg.attractor.istore.controller;

import kg.attractor.istore.dto.CustomerDTO;
import kg.attractor.istore.model.CustomerRegisterForm;
import kg.attractor.istore.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping
    public Page<CustomerDTO> getAll(Pageable pageable){
        return customerService.getAll(pageable);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String registerPage(@Valid @RequestBody CustomerRegisterForm customerRequestDto,
                               BindingResult validationResult,
                               RedirectAttributes attributes) {
        attributes.addFlashAttribute("dto", customerRequestDto);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }

        customerService.register(customerRequestDto);
        return "redirect:/login";
    }
}
