export const uploadToCloudnary = async (pics: any) => {
  const cloud_name = "dyj7dhauv";      
  const upload_preset = "Catify";

  if (pics) {
    const data = new FormData();
    data.append("file", pics);
    data.append("upload_preset", upload_preset);
    data.append("cloud_name", cloud_name);
    const res = await fetch(
      `https://api.cloudinary.com/v1_1/${cloud_name}/image/upload`,
      {
        method: "POST",
        body: data,
      }
    );

    const fileData = await res.json();
    return fileData.secure_url || fileData.url;
  } else {
    console.log("error : pics not found");
  }
};
