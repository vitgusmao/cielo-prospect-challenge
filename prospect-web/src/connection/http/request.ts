const DEFAULT_HEADERS: HeadersInit = {
  accept: 'application/json',
};

type RequestInit = Parameters<typeof fetch>[1];

export default async function request(
  url: string,
  options: RequestInit = {}
): Promise<Response> {
  const headers: Headers = new Headers({
    ...DEFAULT_HEADERS,
    ...options?.headers,
  });

  let body: BodyInit | undefined;
  if (options.body) {
    body = JSON.stringify(options.body);
  }

  const fetchResponse = await fetch(url, {
    ...options,
    body,
    headers,
  });

  return fetchResponse;
}
