export const formatCurrency = (amount) => {
  // Chuyển đổi số thành chuỗi và thêm dấu phẩy
  const formattedAmount = amount
    .toString()
    .replace(/\B(?=(\d{3})+(?!\d))/g, ",");

  // Định dạng với hai chữ số thập phân
  return `${formattedAmount}.000 VNĐ`;
};
