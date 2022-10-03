package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    @Autowired
    private AdRepository adRepository;

    @PostMapping
    public Ad create(@RequestBody Ad ad){
        ad.setId(null);
        ad.setCreation(null);
        return adRepository.save(ad);
    }

    @PutMapping
    public ResponseEntity<Ad> update(@RequestBody Ad ad){
        Ad result = adRepository.modify(ad);
        if(result == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(ad);
    }

    @GetMapping
    public List<Ad> getByPrice(@RequestParam(required = false,defaultValue = "0") int minPrice, @RequestParam(required = false,defaultValue = "10000000") int maxPrice){
        List<Ad> ads = adRepository.findByPrice(minPrice,maxPrice);
        for (Ad ad : ads){
            ad.setSecretCode(null);
        }
        return ads;
    }

    @GetMapping("{tag}")
    public List<Ad> getByTag(@PathVariable String tag, @RequestParam(required = false,defaultValue = "0") int minPrice, @RequestParam(required = false,defaultValue = "10000000") int maxPrice){
        List<Ad> ads = getByPrice(minPrice, maxPrice);
        ads.removeIf(ad -> !ad.getTags().contains(tag));
        return ads;
    }
}
