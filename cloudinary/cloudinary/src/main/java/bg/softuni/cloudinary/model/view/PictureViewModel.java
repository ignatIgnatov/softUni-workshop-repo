package bg.softuni.cloudinary.model.view;

public class PictureViewModel {


  private String title;
  private String publicId;
  private String imgUrl;

  public String getTitle() {
    return title;
  }

  public PictureViewModel setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getPublicId() {
    return publicId;
  }

  public PictureViewModel setPublicId(String publicId) {
    this.publicId = publicId;
    return this;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public PictureViewModel setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
    return this;
  }
}
