package p12.ocr.web.technical.menu.marche;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@RequiredArgsConstructor
public @Data class MenuOrganization {

    @NonNull
    private String name;

    @NonNull
    private List<MenuGender> menuGenderList;



}
