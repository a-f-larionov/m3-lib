package m3.lib.dto;


import lombok.*;
import m3.lib.enums.ObjectEnum;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class ProductDto {

    private final Long index;
    private final Long priceVotes;
    private final Long priceGold;
    private final Long quantity;
    private final String imageSrc;
    private final ObjectEnum objectEnum;

}