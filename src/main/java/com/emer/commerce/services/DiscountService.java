package com.emer.commerce.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emer.commerce.domain.Discount;
import com.emer.commerce.dto.incoming.CreateDiscountCommand;
import com.emer.commerce.dto.incoming.UpdateDiscountCommand;
import com.emer.commerce.dto.outgoing.DiscountHistoryListItem;
import com.emer.commerce.dto.outgoing.DiscountListItem;
import com.emer.commerce.repositories.DiscountRepository;

@Service
public class DiscountService {

    DiscountRepository discountRepository;
    ProductService productService;

    @Autowired
    public DiscountService(DiscountRepository discountRepository, ProductService productService) {
        this.discountRepository = discountRepository;
        this.productService = productService;
    }

    public List<DiscountListItem> findAllActive() {
        return discountRepository.findAllActive()
                .stream()
                .map(DiscountListItem::new)
                .toList();
    }

    public void create(long productId, CreateDiscountCommand command) {
        Discount discount = Discount.builder()
                .name(command.getName().orElse(null))
                .description(command.getDescription().orElse(null))
                .discount_percent(command.getPercentage())
                .build();
        discount.setActive(command.getActive().get());
        if (discount.getActive() == true) {
            discount.setScheduled_start(LocalDateTime.now());
            discount.setScheduled_end(command.getScheduled_end()
                    .orElseThrow(() -> new RuntimeException("End date is required for active discounts")));
        } else {
            discount.setScheduled_start(command.getScheduled_start().orElse(null));
            discount.setScheduled_end(command.getScheduled_end().orElse(null));
        }
        productService.findProductById(productId).setDiscount(discount);
        discountRepository.save(discount);
    }

    public List<DiscountHistoryListItem> findAllForProduct(Long id) {
        return discountRepository.findHistoryForProduct(id)
                .stream()
                .map(DiscountHistoryListItem::new)
                .toList();
    }

    public DiscountListItem findById(Long id) {
        discountRepository.findById(id);
        return new DiscountListItem(productService.findProductByDiscountId(id));
    }

    public void delete(Long id) {
        discountRepository.softDelete(discountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount not found by id: " + id)));
    }

    public void update(Long id, UpdateDiscountCommand command) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount not found by id: " + id));
        command.getName().ifPresent(discount::setName);
        command.getDescription().ifPresent(discount::setDescription);
        command.getPercentage().ifPresent(discount::setDiscount_percent);
        if (command.getActive().get() == true && discount.getActive() == false) {
            discount.setScheduled_start(LocalDateTime.now());
            command.getScheduled_end()
                    .orElseThrow(() -> new RuntimeException("End date is required for active discounts"));
        } else
            command.getScheduled_start().ifPresent(discount::setScheduled_start);
        command.getActive().ifPresent(discount::setActive);
        command.getScheduled_end().ifPresent(discount::setScheduled_end);
        discountRepository.save(discount);
    }
}
