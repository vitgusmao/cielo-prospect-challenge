export function hexToRgb(hex: string) {
  const result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
  return result
    ? {
        r: parseInt(result[1], 16),
        g: parseInt(result[2], 16),
        b: parseInt(result[3], 16),
      }
    : null;
}

export function hexToRgbText(hex: string, transparency = 100) {
  const rgb = hexToRgb(hex);
  return `rgb(${rgb?.r} ${rgb?.r} ${rgb?.r} / ${transparency}%)`;
}

export function rgbToHex(r: number, g: number, b: number): string {
  return `#${componentToHex(r)}${componentToHex(g)}${componentToHex(b)}`;
}

function componentToHex(c: number): string {
  const hex = c.toString(16);
  return hex.length === 1 ? `0${hex}` : hex;
}

export default {
  primaryColor: '#017ceb',
  secondaryColor: '#5a646e',
  background: '#ececec',
  primaryTitleColor: '#f0f0f0',
  secondaryTitleColor: '#47667f',
  primaryTextColor: '#202E39',
};
