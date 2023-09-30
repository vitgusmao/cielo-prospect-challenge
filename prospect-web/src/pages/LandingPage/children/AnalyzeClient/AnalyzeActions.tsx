import { Link } from 'react-router-dom';
import styled from 'styled-components';

import { ClientType } from '../../../../@types';
import { getApiEndpoint } from '../../../../data';
import Button from '../../../../facades/Button';
import Space from '../../../../facades/Space';
import useHttpPut from '../../../../hooks/useHttpPut';

const StyledDiv = styled.div`
  display: flex;
  justify-content: space-around;
`;

function defineStatus(status: ClientType['status']) {
  return { status };
}

function AnalyzeActions({ id }: { id: number }) {
  const put = useHttpPut(getApiEndpoint(`/prospects/${id}`));
  return (
    <StyledDiv>
      <Space>
        <Button
          onClick={() => {
            put(defineStatus('ACCEPTED'));
          }}
        >
          <Link to="/clients">Aprovar</Link>
        </Button>
        <Button
          onClick={() => {
            put(defineStatus('REJECTED'));
          }}
        >
          <Link to="/clients">Reprovar</Link>
        </Button>
      </Space>
      <Button
        onClick={() => {
          put(defineStatus('NOT_PROCESSED'));
        }}
      >
        <Link to="/clients">Cancelar</Link>
      </Button>
    </StyledDiv>
  );
}

export default AnalyzeActions;
