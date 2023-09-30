export default function validateCPF(cpf: string) {
  let sum = 0;
  let remainder = 0;

  for (let i = 1; i <= 9; i += 1) {
    sum += parseInt(cpf.charAt(i - 1), 10) * (11 - i);
  }

  remainder = (sum * 10) % 11;

  if (remainder === 10 || remainder === 11) {
    remainder = 0;
  }

  if (remainder !== parseInt(cpf.charAt(9), 10)) {
    return false;
  }

  sum = 0;
  for (let i = 1; i <= 10; i += 1) {
    sum += parseInt(cpf.charAt(i - 1), 10) * (12 - i);
  }

  remainder = (sum * 10) % 11;

  if (remainder === 10 || remainder === 11) {
    remainder = 0;
  }

  if (remainder !== parseInt(cpf.charAt(10), 10)) {
    return false;
  }

  return true;
}
