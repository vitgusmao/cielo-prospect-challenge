import { Divider } from 'antd';
import Button from 'antd/es/button';
import Space from 'antd/es/space';
import { FaBolt } from 'react-icons/fa';
import styled from 'styled-components';

import Title from '../../../components/Title';

const StyledDiv = styled.div`
  .title {
    margin-top: 0;
    @media screen and (max-width: 498px) {
      font-size: 24px;
    }
  }

  .big-button {
    height: 180px;
    width: 200px;
  }
`;

function QuickActions() {
  return (
    <StyledDiv>
      <Title iconComponent={FaBolt} label="Ações rápidas" level={2} />
      <Divider />
      <Space size="large" wrap>
        <Button className="big-button">Adicionar pré registro</Button>
        <Button className="big-button">Analisar pré registro</Button>
      </Space>
    </StyledDiv>
  );
}

export default QuickActions;
