package m3.lib.store;

import m3.lib.dto.ProductDto;
import m3.lib.enums.ObjectEnum;

import java.util.List;

import static m3.lib.enums.ObjectEnum.*;

public class ShopStore {

    public static final ProductDto health = buildProductDto(0L, 200L, 5L, STUFF_HEALTH, "");
    public static final ProductDto turnsUp = buildProductDto(0L, 200L, 10L, STUFF_TURNS, "");

    public static final List<ProductDto> gold = List.of(
            buildProductDto(0L, 1L, 500L, 500L, STUFF_GOLD, ""),
            buildProductDto(1L, 2L, 1100L, 1100L, STUFF_GOLD, ""),
            buildProductDto(2L, 3L, 1500L, 1500L, STUFF_GOLD, "")
    );

    public static final List<ProductDto> hummers = List.of(
            buildProductDto(0L, 500L, 3L, STUFF_HUMMER, "hummer-big.png"),
            buildProductDto(1L, 1000L, 6L, STUFF_HUMMER, "hummer-big.png"),
            buildProductDto(2L, 1500L, 9L, STUFF_HUMMER, "hummer-big.png")
    );

    public static final List<ProductDto> shuffle = List.of(
            buildProductDto(0L, 500L, 3L, STUFF_SHUFFLE, "shuffle-big.png"),
            buildProductDto(1L, 1000L, 6L, STUFF_SHUFFLE, "shuffle-big.png"),
            buildProductDto(2L, 1500L, 9L, STUFF_SHUFFLE, "shuffle-big.png")
    );

    public static final List<ProductDto> lightning = List.of(
            buildProductDto(0L, 500L, 3L, STUFF_LIGHTNING, "lightning-big.png"),
            buildProductDto(1L, 1000L, 6L, STUFF_LIGHTNING, "lightning-big.png"),
            buildProductDto(2L, 1500L, 9L, STUFF_LIGHTNING, "lightning-big.png")
    );

    public static boolean goldProductByPriceExists(Long price) {
        return gold.stream()
                .anyMatch(
                        product -> product.getPriceVotes().equals(price)
                );
    }

    public static ProductDto getGoldProductByPrice(Long price) {
        return gold.stream()
                .filter(product -> product.getPriceVotes().equals(price))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Product not found."));
    }

    public static ProductDto getProduct(Long index, List<ProductDto> products) {
        return products.stream()
                .filter(p -> p.getIndex().equals(index))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Product no found." +
                        " Requested index: " + index +
                        " index founded: "
                        + String.join(",", products.stream()
                        .map(p -> p.getIndex().toString())
                        .toList()))
                );
    }

    public static ProductDto getProduct(Long index, ObjectEnum objectId) {
        switch (objectId) {
            case STUFF_HUMMER -> {
                return getHummer(index);
            }
            case STUFF_LIGHTNING -> {
                return getLightning(index);
            }
            case STUFF_SHUFFLE -> {
                return getShuffle(index);
            }
//            case STUFF_GOLD -> {
//                return getGoldProductByPrice(index);
//            }
        }
        throw new RuntimeException("Product objectId not found.");
    }

    public static ProductDto getHummer(Long index) {
        return getProduct(index, hummers);
    }

    public static ProductDto getLightning(Long index) {
        return getProduct(index, lightning);
    }

    public static ProductDto getShuffle(Long index) {
        return getProduct(index, shuffle);
    }

    private static ProductDto buildProductDto(Long index, Long priceGold, Long quantity, ObjectEnum dataObject, String imageSrc) {
        return buildProductDto(index, null, priceGold, quantity, dataObject, imageSrc);
    }

    private static ProductDto buildProductDto(Long index, Long priceVotes, Long priceGold, Long quantity, ObjectEnum objectEnum, String imageSrc) {
        return ProductDto.builder()
                .index(index)
                .priceVotes(priceVotes)
                .priceGold(priceGold)
                .quantity(quantity)
                .objectEnum(objectEnum)
                .imageSrc(imageSrc)
                .build();
    }

}
