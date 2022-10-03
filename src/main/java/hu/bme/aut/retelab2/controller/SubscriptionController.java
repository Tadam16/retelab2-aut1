package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Subscription;
import hu.bme.aut.retelab2.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ads/{id}/subscribe")
public class SubscriptionController {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @PostMapping
    public Subscription subscribe(@PathVariable Long id,@RequestBody Subscription subscription){
        subscription.setId(null);
        subscription.setSubscribedTo(id);
        return subscriptionRepository.save(subscription);
    }
}
