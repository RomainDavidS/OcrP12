package p12.ocr.web.technical.menu.marche;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
public @Data class MenuGender {

    @NonNull
    private String name;

    @NonNull
    private List<SimpleMenuMarche> simpleMenuMarcheList;

}
