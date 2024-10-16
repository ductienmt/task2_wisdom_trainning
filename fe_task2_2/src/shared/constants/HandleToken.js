export const handleToken = {
  saveToken: (token, username, role) => {
    const tokenExpiryTime = 5 * 60 * 1000; // 5 minutes
    const expiryDate = new Date(Date.now() + tokenExpiryTime);

    localStorage.setItem("accessToken", token);
    localStorage.setItem("username", username);
    localStorage.setItem("role", role);
    localStorage.setItem("tokenExpiry", expiryDate.toISOString());

    console.log("Token saved successfully.");
  },

  isTokenExpired: () => {
    const expiry = localStorage.getItem("tokenExpiry");
    return expiry ? new Date(expiry) < new Date() : true;
  },
};
