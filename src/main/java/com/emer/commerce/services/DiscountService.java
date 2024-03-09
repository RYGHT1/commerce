package com.emer.commerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emer.commerce.domain.Discount;
import com.emer.commerce.dto.incoming.CreateDiscountCommand;
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
                .active(command.getActive().get())
                .build();
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
}
