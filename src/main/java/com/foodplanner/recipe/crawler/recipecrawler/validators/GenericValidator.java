package com.foodplanner.recipe.crawler.recipecrawler.validators;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class GenericValidator<T> implements Predicate<T> {

    private final List<Predicate<T>> validators = new LinkedList<>();

    public GenericValidator(List<Predicate<T>> validators) {
        this.validators.addAll(validators);
    }

    // @Override
    // public boolean test(T toValidate) {
    // return validators.parallelStream()
    // .allMatch(predicate -> predicate.test(toValidate));
    // }

    @Override
    public boolean test(T toValidate) {
        return validators.parallelStream()
                .allMatch(predicate -> predicate.test(toValidate));
    }

}
