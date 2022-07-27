package api.models;

import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class PostApi {

    @Getter
    @Setter
    @NonNull
    private int userId;
    @Getter
    @Setter
    @NonNull
    private String title;
    @Getter
    @Setter
    @NonNull
    private String body;
    @Getter
    @Setter
    private int id;
}