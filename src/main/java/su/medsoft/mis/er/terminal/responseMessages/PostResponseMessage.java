package su.medsoft.mis.er.terminal.responseMessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import su.medsoft.mis.er.terminal.dto.PostDto;

import java.util.List;

public class PostResponseMessage extends BaseResponseMessage {
    @JsonProperty("posts")
    @SerializedName("posts")
    private List<PostDto> postDtoList;

    public List<PostDto> getPostDtoList() {
        return postDtoList;
    }

    public void setPostDtoList(List<PostDto> postDtoList) {
        this.postDtoList = postDtoList;
    }
}
