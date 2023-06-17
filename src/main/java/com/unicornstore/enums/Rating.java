package com.unicornstore.enums;

public enum Rating {
    BAD(1),
    FAIR(2),
    GOOD(3),
    VERYGOOD(4),
    EXCELLENT(5);
    private final int rating;
    Rating(int rating) {
        this.rating = rating;
    }
}
