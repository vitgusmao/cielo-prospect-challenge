import { ClientType, LegalEntity } from './@types';

export function isLegalEntity(client: ClientType): client is LegalEntity {
  return 'cnpj' in client;
}
