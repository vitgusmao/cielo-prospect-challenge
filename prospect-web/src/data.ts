const { VITE_API_URL } = import.meta.env;

export const API_URL = (() => {
  if (!VITE_API_URL) {
    throw new Error('VITE_API_URL must be defined in dotenv file.');
  }

  return VITE_API_URL;
})();

export function getApiEndpoint(url: string) {
  return `${VITE_API_URL}${url}`;
}
