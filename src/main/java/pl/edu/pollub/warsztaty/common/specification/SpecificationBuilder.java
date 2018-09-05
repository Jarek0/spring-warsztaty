package pl.edu.pollub.warsztaty.common.specification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.*;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public abstract class SpecificationBuilder<T, F extends Filter> {

    private final Class<F> filterClass;

    public Specification<T> create(F filter) {

        Specifications<T> specifications = Specifications.where(alwaysTrue());

        for (Field field : filterClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(FilterBy.class)) {
                createSpecification(filter, field).ifPresent(specifications::and);
            }
        }

        return specifications;
    }

    private Optional<Specification<T>> createSpecification(F filter, Field field) {
        FilterBy filterBy = field.getAnnotation(FilterBy.class);
        String name = filterBy.field();
        Object value = tryGetFieldValue(field, filter);
        if(isNull(value)) return Optional.empty();
        Specification<T> result = null;
        switch (filterBy.constraints()) {
            case EQ:
                result = equal(name, value);
                break;
            case LIKE:
                result = like(name, value);
                break;
        }
        return Optional.of(result);
    }

    private Specification<T> equal(String name, Object value) {
        return (root, query, cb) -> cb.equal(root.get(name), value);
    }

    private Specification<T> like(String name, Object value) {
        //noinspection unchecked
        return (root, query, cb) -> cb.like( cb.lower(root.get(name)), "%" + value + "%");
    }

    private Path getPath(Root<T> root, String name) {
        List<String> path = new ArrayList<>();
        Collections.addAll(path, name.split("."));
        if(path.isEmpty()) path.add(name);
        int firstStepNumber = 0;
        Path finalPath = root.get(path.get(firstStepNumber));
        path.remove(firstStepNumber);
        for(String stepInPath : path) {
            finalPath = root.get(stepInPath);
        }
        return finalPath;
    }

    private Object tryGetFieldValue(Field field, Filter filter) {
        try {
            field.setAccessible(true);
            return field.get(filter);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Filed: " + field + " not exists in filter");
        }
    }

    private Specification<T> alwaysTrue() {
        return (root, query, cb) -> cb.isNotNull(getPath(root, "id"));
    }
}
