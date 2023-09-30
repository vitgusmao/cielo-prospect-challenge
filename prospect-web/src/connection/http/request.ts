const DEFAULT_HEADERS: HeadersInit = {
  accept: 'application/json',
};

type Options = { body?: object } & Omit<RequestInit, 'body'>;

export default async function request(
  url: string,
  options: Options = {}
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
